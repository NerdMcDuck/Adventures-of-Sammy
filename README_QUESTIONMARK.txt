If using eclipse, import your project. File -> Import -> Existing Project to workspace(or whatever it is) 
I've set up the project so that all necessary jars and libraries reference each other and not dependent on the full file path. 
If any issues (Class not define or some nonsense like that) 
	Project -> Properties -> Java Build Path -> Libraries 
		under lwjgl.jar make sure that native library location is set to Adventure_of_Sammy/lib/natives/windows <- change if not using windows

If not using eclipse,  ¯\_(ツ)_/¯

All files images, music, sound, etc. goes in the res folder. 

Game: is pretty much setup, nothing to do there(I think) 
Menu: the stuff here is me testing out things until we actually have actual images and sound files
Play: Where the actual playing is going to take place. Feel free to add more states.
Player: The player object, I just made the class but not in here yet. We haven't really discussed what our character attributes are going to be (health, mentality bar? idk) 


.     .       .  .   . .   .   . .    +  .
  .     .  :     .    .. :. .___---------___.
       .  .   .    .  :.:. _".^ .^ ^.  '.. :"-_. .
    .  :       .  .  .:../:            . .^  :.:\.
        .   . :: +. :.:/: .   .    .        . . .:\
 .  :    .     . _ :::/:               .  ^ .  . .:\
  .. . .   . - : :.:./.                        .  .:\
  .      .     . :..|:                    .  .  ^. .:|
    .       . : : ..||        .                . . !:|
  .     . . . ::. ::\(                           . :)/
 .   .     : . : .:.|. ######              .#######::|
  :.. .  :-  : .:  ::|.#######           ..########:|
 .  .  .  ..  .  .. :\ ########          :######## :/
  .        .+ :: : -.:\ ########       . ########.:/
    .  .+   . . . . :.:\. #######       #######..:/
      :: . . . . ::.:..:.\           .   .   ..:/
   .   .   .  .. :  -::::.\.       | |     . .:/
      .  :  .  .  .-:.":.::.\             ..:/
 .      -.   . . . .: .:::.:.\.           .:/
.   .   .  :      : ....::_:..:\   ___.  :/
   .   .  .   .:. .. .  .: :.:.:\       :/
     +   .   .   : . ::. :.:. .:.|\  .:/|
     .         +   .  .  ...:: ..|  --.:|
.      . . .   .  .  . ... :..:.."(  ..)"
 .   .       .      :  .   .: ::/  .  .::\