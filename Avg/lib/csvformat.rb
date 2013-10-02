#! ruby env

require "FasterCSV"

FasterCSV.open("finalseptdata.csv","r") do |csv|

	csv.split(",")
end

	
	
