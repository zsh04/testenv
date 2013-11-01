#!env ruby
#/opt/sensu/embedded/bin/ruby

# PubApi monitoring tool
# ===
#
# DESCRIPTION:
#   This plugin uses net/http to connect to a host and get json formatted stats. 
#   Logic will then be applied and one of two exit codes will be set based on the results.
#   Messages will be sent along with the alert to identify the problem.
#   
# USAGE:
#
#   To see a list of options use ./pubapicheck.rb -h

require 'rubygems' if RUBY_VERSION < '1.9.0'
require 'sensu-plugin/check/cli'
require 'json'
require 'net/http'
require 'openssl'

class PubApiCheck < Sensu::Plugin::Check::CLI

  option :host,
    :short => "-H HOST",
    :long => "--host HOST",
    :description => "HOST to get zstats from",
    :default => "localhost"

  option :port,
    :short => "-p PORT",
    :long => "--port PORT",
    :description => "Port to connect to",
    :default => "443"

  option :path,
    :short => "-P PATH",
    :long => "--path PATH",
    :description => "PATH to zstats",
    :default => "/publicapi-v1/zstats"

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
    :default => "X-NG-PAK"

  option :header_value,
    :short => "-v value",
    :long => "--header_value value",
    :description => "The value of the addition header",
    :default => nil

  option :ssl,
    :short => "-s",
    :boolean => true, 
    :description => "Use ssl when connecting",
    :default => true

  option :debug,
    :short => "-d",
    :long => "--debug",
    :boolean => true, 
    :description => "Debug mode",
    :default => false

  option :help,
    :long => "--help",
    :short => "-h",
    :description => "Show this message",
    :on => :tail,
    :show_options => true,
    :boolean => true,
    :exit => 0
  
  def run
    timestamp = Time.now.to_i
    exitStatus = 0
    stats = {}
    msg = []

    value = JSON.parse getJsonfromApi # call the api and slurp all results
    value.each do
      |k,v| stats[k] = v
    end
    @apiMetrics = {
      'pubapi'=> stats
    }

    # find the stats for specific metrics in the apiMetrics hash
    scannerCycleFinish = getMetricifExists('wl.scanner.cycle','lastFinishSec')
    scannerReadFailure = getMetricifExists('wl.scanner.read.failure.count','lastFinishSec')
    catalogCacheUpdate = getMetricifExists('wl.catalog.cache.update','lastFinishSec') 
    catalogCacheUpdateFailures = getMetricifExists('wl.catalog.cache.update.failures','lastFinishSec') 

    if config[:debug] == true
      p "scannerCycleFinish = #{scannerCycleFinish}"
      p "scannerReadFailure = #{scannerReadFailure}"
      p "catalogCacheUpdate = #{catalogCacheUpdate}"
      p "catalogCacheUpdateFailures = #{catalogCacheUpdateFailures}"
    end
  
    # assay section
    if scannerCycleFinish 
      if (timestamp - 3600) > scannerCycleFinish
        msg << 'Scanner finish time is older than one hour: The scanner may be hung.'
        exitStatus += 1
      end
    end
    if (scannerReadFailure and scannerCycleFinish)
      if scannerReadFailure > scannerCycleFinish
        msg << 'Scanner read failure finish time is newer that scanner finish time: There may be read errors.'
        exitStatus += 1
      end
    end
    if catalogCacheUpdate
      if (timestamp - 3600) > catalogCacheUpdate
        msg << 'Cache update finish time is older than one hour.'
        exitStatus += 1
      end
    end
    if (catalogCacheUpdateFailures and catalogCacheUpdate)
      if catalogCacheUpdateFailures > catalogCacheUpdate
        msg << 'Cache failure.'
        exitStatus += 1
      end
    end
    # assay end

    # exit status logic
    unless exitStatus.nonzero?
      ok
    else
      critical message(msg.to_s)
    end
  end

  def getMetricifExists(metric_name,attr_name)
    if @apiMetrics['pubapi'][metric_name]
       @apiMetrics['pubapi'][metric_name][attr_name]
    end
  end

  def getJsonfromApi
    http = Net::HTTP.new(config[:host], config[:port])
    req = Net::HTTP::Get.new(config[:path])
    req.add_field 'User-Agent', 'Sensu-Monitoring-Plugin'
    
    headerValue = config[:header_value]
    password = config[:password]
    header = config[:header]
    debug = config[:debug]
    user = config[:user]
    host = config[:host]
    ssl = config[:ssl]

    if ssl == true
      http.use_ssl = true
      http.verify_mode = OpenSSL::SSL::VERIFY_NONE
    end
    if debug == true
      http.set_debug_output($stdout)
    end
    if (user != nil and password != nil)
      req.basic_auth user, password
    end
    if (header != nil and headerValue != nil)
      req.add_field header, headerValue
    end
    
    res = http.request(req) # make the connection
  
    case res.code
    when "200"
        res.body
    else
        critical message("Error: #{res.code} recieved from #{host}.")
    end
  end
end # end of class
