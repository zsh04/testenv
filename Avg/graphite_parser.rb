#!/usr/bin/env ruby -w

require 'rubygems' if RUBY_VERSION < '1.9.0'
require 'sensu-plugin/metric/cli'
require 'json'
require 'net/http'
require 'openssl'
require 'pp'

class ZedStats < Sensu::Plugin::Metric::CLI::Graphite

  option :host,
    :short => "-H HOST",
    :long => "--host HOST",
    :description => "HOST to get zstats from",
    :default => "graphite.ops.nastygal.com"

  option :port,
    :short => "-p PORT",
    :long => "--port PORT",
    :description => "Port to connect to",
    :default => "80"

  option :path,
    :short => "-P PATH",
    :long => "--path PATH",
    :description => "PATH to check zstat output",
    :default => "#{Socket.gethostname}/zstats"

  option :user,
    :short => "-user USER",
    :long => "--user USER",
    :description => "User if HTTP Basic is used",
    :default => nil

  option :password,
    :short => "-password USER",
    :long => "--password USER",
    :description => "Password if HTTP Basic is used",
    :default => nil

  option :scheme,
    :description => "Metric naming scheme, text to prepend to .$parent.$child",
    :long => "--scheme SCHEME",
    :default => "stats.#{Socket.gethostname}"

  option :ssl,
    :short => "-s",
    :boolean => true,
    :description => "Use ssl when connecting",
    :default => false

  option :header,
    :long => "--header value",
    :description => "Addition header to include in request",
    :default => nil

  option :header_value,
    :short => "-v value",
    :long => "--header_value value",
    :description => "The value of the addition header",
    :default => nil

  option :debug,
    :short => "-d",
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

  def run(path)
    timestamp = Time.now.to_i
    stats = {}

    lines = JSON.parse getJsonfromApi(path)

    lines.each do |h|
    val = []
      h["datapoints"].each do |a|
        val << a[0] if a[0] != nil
      end
      avg = val.inject(0.0) { |sum, el| sum + el } / val.size
      target = h["target"]
      printf "%-90s %-30s\n", h["target"], avg
    end
    ok
  end

  def getJsonfromApii(path)
    headerValue = config[:header_value]
    password = config[:password]
    header = config[:header]
    debug = config[:debug]
    port = config[:port]
    #path = config[:path]
    user = config[:user]
    host = config[:host]
    ssl = config[:ssl]

    http = Net::HTTP.new(host, port)
    req = Net::HTTP::Get.new(path)
    req.add_field 'User-Agent', 'Sensu-Monitoring-Plugin'

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
