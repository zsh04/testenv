#!/usr/bin/env ruby

require 'rubygems' if RUBY_VERSION < '1.9.0'
require 'sensu-plugin/check/cli'
require 'json'
require 'net/http'
require 'openssl'

class ZedCheck < Sensu::Plugin::Check::CLI

option :host,
  :short => "-H HOST",
  :long => "--host HOST",
  :description => "HOST to check zstat output",
  :default => "localhost"

  option :port,
    :short => "-p PORT",
    :long => "--port PORT",
    :description => "Port to check zstat output",
    :default => "8080"

  option :path,
    :short => "-P PATH",
    :long => "--path PATH",
    :description => "PATH to check zstat output",
    :default => nil

  option :user,
    :long => "--user USER",
    :description => "User if HTTP Basic is used",
    :default => nil

  option :password,
    :long => "--password USER",
    :description => "Password if HTTP Basic is used",
    :default => nil

  option :header,
    :long => "--header value",
    :description => "Addition header to include in request",
    :default => nil

  option :header_value,
    :short => "-v value",
    :long => "--header_value value",
    :description => "The value of the addition header",
    :default => nil

  option :ssl,
    :short => "-s",
    :boolean => true, 
    :description => "Use ssl when connecting"

  option :debug,
    :short => "-d",
    :boolean => true, 
    :description => "Debug mode"

  option :scheme,
    :description => "Metric naming scheme, text to prepend to .$parent.$child",
    :long => "--scheme SCHEME",
    :default => "#{Socket.gethostname}"

  option :help,
    :long => "--help",
    :description => "Show this message",
    :on => :tail,
    :show_options => true,
    :boolean => true,
    :exit => 0
  
  def run
    timestamp = Time.now.to_i
    stats = Hash.new
    value = JSON.parse get_mod_status
    value.each do |k,v|
        stats[k] = v
    end
    exitStatus = 0
    @metrics = {
      'zcheck'=> stats
    }

    scanner_cycle_finish = get_metric_if_exists('wl.scanner.cycle','lastFinishSec')
    scanner_read_failure = get_metric_if_exists('wl.scanner.read.failure.count','lastFinishSec')

    if config[:debug] == true
      p "#{timestamp} - 3600 > #{scanner_cycle_finish}" 
      p "#{scanner_read_failure} > #{scanner_cycle_finish}" 
    end
   
    if scanner_cycle_finish
      # wl.scanner.cycle/lastFinishSec date is older that one hour from now: this will detect scanner hanging
      if (timestamp - 3600) > scanner_cycle_finish
        p "Scanner finish time is older than one hour: The scanner may be hung."
        exitStatus += 1
      end
    end
    if (scanner_read_failure and scanner_cycle_finish)
      # wl.scanner.read.failure.count/lastFinish time is newer than wl.scanner.cycle/lastFinish date: this will signal for new read errors
      if scanner_read_failure > scanner_cycle_finish
        p "Scanner read failure finish time is newer that scanner finish time: There may be read errors."
        exitStatus += 1
      end 
    end

    unless exitStatus.nonzero?
      ok #return ok
    else
      exit exitStatus
    end
  end

  def get_metric_if_exists(metric_name,value_name)
    if @metrics['zcheck'][metric_name]
       @metrics['zcheck'][metric_name][value_name]
    end
  end

  def get_mod_status
      http = Net::HTTP.new(config[:host], config[:port])
      req = Net::HTTP::Get.new(config[:path])
      req.add_field 'User-Agent', 'Sensu-Monitoring-Plugin'

      if config[:ssl] == true
        http.use_ssl = true
        http.verify_mode = OpenSSL::SSL::VERIFY_NONE
      end
      if config[:debug] == true
        http.set_debug_output($stdout)
      end
      if (config[:user] != nil and config[:password] != nil)
        req.basic_auth config[:user], config[:password]
      end
      if (config[:header] != nil and config[:header_value] != nil)
        req.add_field config[:header], config[:header_value]
      end
      
      res = http.request(req) # do the work
  
      case res.code
      when "200"
          res.body
      else
          critical "Error: #{res.code}"
      end
  end
end # end of class
