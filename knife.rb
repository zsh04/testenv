current_dir = File.dirname(__FILE__)
log_level                :info
log_location             STDOUT
node_name                "zsh"
client_key               "#{current_dir}/zsh.pem"
validation_client_name   "nastygal04-validator"
validation_key           "#{current_dir}/nastygal04-validator.pem"
chef_server_url          "https://api.opscode.com/organizations/nastygal04"
cache_type               'BasicFile'
cache_options( :path => "#{ENV['HOME']}/.chef/checksums" )
cookbook_path            ["#{current_dir}/../cookbooks"]
