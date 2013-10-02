#!/opt/chef/embedded/bin/ruby
system ('clear')

def check_max_number(max)
    !!Float(max) rescue false
end

def check_g_number(g)
    !!Float(g) rescue false
end

def max_convert(max)
    while !check_max_number(max)
    puts "please enter only a number: "
    max = gets.chomp
    end
    return max
end

def g_convert(g)
    while !check_g_number(g)
    puts "please enter only a number: "
    g = gets.chomp
    end
    g = g.to_i
    return g
end

def guess_check_against(guess,g,max)    
         until !guess.include?(g) && g.between?(0,max)
	       puts "Oops, We encountered an error, please make sure to enter a different guess between 0 " + max.to_s + " : "
 	       g = gets.chomp
 	       check_g_number(g)
 	       g = g_convert(g)
	       end
	       return g
end


puts "what is your name?"
user= gets.chomp
user.split.map(&:capitalize).join(' ')

puts "Hello, #{user.split.map(&:capitalize).join(' ')}!!\n"
win = 0
max_user_attempts = 4
puts "Lets play a guessing game! You have " + max_user_attempts.to_s + " guesses before you lose."
play = true

while play == true
	
	g = 0
  count = 0
  attempt_counter = 0
  guess = Array.new()
	  
	  print "Please tell me the max value of the random number: "
	    max= gets.chomp
	    check_max_number(max)
	    max = max_convert(max)
	    max = max.to_i
	    num= rand(max)
	  puts "Ok. The random number is generated between 0 and " + max.to_s + "."
	  print "Make your guess: "
          g = gets.chomp
          check_g_number(g)
          g = g_convert(g)
          g = g.to_i
          guess_check_against(guess,g,max)
          guess << g
    	      attempt_counter+=1
    	      count+=1
    	      
    	while guess != num && play != false
              
      if g > num && attempt_counter < max_user_attempts
        print "That's too high. Guess again: "
        g = gets.chomp
        check_g_number(g)
        g = g_convert(g)
        g = g.to_i
        g = guess_check_against(guess,g,max)
        guess << g
        attempt_counter+=1
        count+=1
      elsif g < num && attempt_counter < max_user_attempts
        print "That's too low. Guess again: "
        g = gets.chomp
        check_g_number(g)
        g = g_convert(g)
        g = g.to_i
        g = guess_check_against(guess,g,max)
        guess << g
        attempt_counter+=1
        count+=1
      else
        break
      end
    
    end
    
  if attempt_counter > max_user_attempts || g != num
          puts "Sorry! you lost! Try Again"
          puts "The winning number was: #{num}"
          print "#{user} your guesses were: #{guess.uniq}\n"
                    
  elsif attempt_counter < max_user_attempts || g == num     
          puts "Correct! You guessed the answer in " + count.to_s + " tries!"
          puts "The winning number was: #{num}"
          win+=1
          print "#{user} your guesses were: #{guess.uniq}\n"
  end
      
      if win >= 3
          puts "Congratulation! you won #{win} games in a row"
           begin
           puts "Please answer in a yes or a no format only!"
	         puts "Would like to play again?"
           answer = gets.chomp
           answer.downcase
           end until answer == "yes" || answer == "no" 
      elsif win < 3
	         begin
           puts "Please answer in a yes or a no format only!"
	         puts "Would like to play again?"
           answer = gets.chomp
           answer.downcase
           end until answer == "yes" || answer == "no"
    	  end
    	if answer == "yes"
        play = true
    end
    if answer == "no"
        play = false
    break
    end  	  	 	   	   	
max_user_attempts = 4    	  
end
puts "Ok. Goodbye!!"

