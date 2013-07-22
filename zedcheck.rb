#!/usr/bin/env ruby

require 'rubygems' if RUBY_VERSION < '1.9.0'
#require 'sensu-plugin/check/cli'
require 'json'
require 'net/http'
require 'openssl'
require 'mixlib/cli'

#class ZedCheck < Sensu::Plugin::Check::CLI
class ZedCheck 
  include Mixlib::CLI

option :host,
  :short => "-h HOST",
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
    :default => "/spiky-bangle/zstat"

  option :user,
    :short => "-u USER",
    :long => "--user USER",
    :description => "User if HTTP Basic is used",
    :default => nil

  option :password,
    :short => "-p USER",
    :long => "--password USER",
    :description => "Password if HTTP Basic is used",
    :default => nil

  option :header,
    :short => "-H value",
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
    :description => "Use ssl"

  option :debug,
    :short => "-d",
    :boolean => true, 
    :description => "Debug mode"

  option :help,
    :show_options => true,
    :long => "--help",
    :description => "Show this message",
    :on => :tail,
    :boolean => true,
    :exit => 0
  
  def run
    timestamp = Time.now.to_i
    stats = Hash.new
    value = JSON.parse get_mod_status
     get_mod_status
    value.each do |k,v|
        stats[k] = v
    end
    metrics = {
      :zstat=> stats
    }
    metrics.each do |parent, children|
      children.each do |child, value|
        output [config[:scheme], parent, child].join("."), value, timestamp
      end
    end
    ok # return ok
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
    puts 'then end of get_mod_status'
  end

end # end of class

z = ZedCheck()
z.run
