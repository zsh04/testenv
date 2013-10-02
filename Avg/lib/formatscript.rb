
data = {}
fnames = []
lines = []
d = Dir.chdir("/Users/zishan.malik/testenv/Avg/data/")
d = Dir.glob("*")
d.each {|x| data[x] = []}
d.each {|x| fnames << x}

for element in fnames 
      File.open(element, 'r').each_line do |line|
           line = line.strip.split '\n'
           lines << line
           data[element] = lines.flatten
      end
  lines = []
end 

data.each do |key,value|

printf "%-30s %-30s\n", data["key"], value

end 



          

