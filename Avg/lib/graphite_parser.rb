#!/usr/bin/env ruby -w

require 'rubygems' if RUBY_VERSION < '1.9.0'
require 'json'
require 'net/http'
require 'openssl'
require 'pp'

class ZedStats
  def get_data(path)
    timestamp = Time.now.to_i
    stats = {}

    lines = JSON.parse getJsonfromApi(path)

    lines.each do |h|
    val = []
    time = []
      h["datapoints"].each do |a|
        val << a[0] if a[0] != nil
       time << a[1] if a[1] != nil
      end
      avg = val.inject(0.0) { |sum, el| sum + el } / val.size
      target = h["target"]
      #printf "%-90s %-30s\n", h["target"], avg
#p "#{target}: #{avg}"
      return target, time, avg
    end
  end

  def getJsonfromApi(path)
    headerValue = nil
    password = nil
    header = nil
    debug = nil
    port = 80
    user = nil
    host = "graphite.ops.nastygal.com"
    ssl = nil

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
