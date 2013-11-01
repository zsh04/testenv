{
  "checks":{
    "artemis_stat": {
      "command": "artemis_stat.rb",
        "subscribers": [
          "staging"
                       ],
  "interval": 60
  "handlers":[
    "graphite",
      ]
    }
  }
}
