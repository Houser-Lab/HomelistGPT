# HomelistGPT
homelist plugin mostly generated by ChatGPT

Pretty simple and the code probably looks crappy. I wanted to test the abilities of ChatGPT and asked it to help me with a plugin that allows players to save home coords and view them in an inventory GUI.
Some stuff was definitely messed up but it made the basics perfectly. Kind of refused to generate the code making a GUI when executing /home list and I noticed that is saves coords to config.yml without any distinction of which player they belong to so it missed on that mark too.

Try it out if you want or suggest more features to test! Enjoy.

Currently:
You can use /sethome <home name> to set a home at your current location

doing /home <home name> will teleport you to that saved home, classic

doing /home list will open a Inventory GUI that displays compasses for each home saved.

All homes are saved to one config file so you would get shown everybody else's homes in the list. 
There is also no check on the name of the home so if you put in the name of a home that already exists it will override it
I might look into adding that distinction and playing around with it but this was mostly just to play around and see what ChatGPT could produce.
