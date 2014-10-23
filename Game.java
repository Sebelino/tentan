import java.util.HashSet;
import java.util.Iterator;

/**
 * This class is the main class of the "Sebastian Olsson's Tentan" application. 
 * "Sebastian Olsson's Tentan" is a fairly complex, text based adventure game.
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser, the player and a timer. It also starts the game
 * and executes the commands that the parser returns.
 * 
 * @author Michael Kolling
 * @author David J. Barnes
 * @author Sebastian Olsson
 * @version 2010.12.04
 */

public class Game{
    private Parser parser;
    private Player player;
    private Timer timer;
    private HashSet<Room> rooms;
    
    private static int PASS_LIMIT = 1000;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game(){
        parser = new Parser();
        timer = new Timer();
        player = new Player(timer.getCurrentTime());
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     * Also determines the room that the player starts in.
     */
    private void createRooms(){
        rooms = new HashSet<Room>();

        Room theaterD1 = new Room(RoomDescription.THEATER_D1);
        Room theaterE1 = new Room(RoomDescription.THEATER_E1);
        Room theaterF1 = new Room(RoomDescription.THEATER_F1);
        Room theaterF2 = new Room(RoomDescription.THEATER_F2);
        Room theaterK1 = new Room(RoomDescription.THEATER_K1);
        Room theaterQ1 = new Room(RoomDescription.THEATER_Q1);
        Room hallV22 = new Room(RoomDescription.HALL_V22);
        Room hallExam = new Room(RoomDescription.HALL_EXAM);
        Room hallKTH = new Room(RoomDescription.HALL_KTH);
        Room library = new Room(RoomDescription.LIBRARY);
        Room infocenter = new Room(RoomDescription.INFOCENTER);
        Room nymble = new Room(RoomDescription.NYMBLE);
        Room escapen = new Room(RoomDescription.ESCAPEN);
        Room alley = new Room(RoomDescription.ALLEY);
        Room lantmateri = new Room(RoomDescription.LANTMATERI);
        Room osquarsBacke = new Room(RoomDescription.OSQUARS_BACKE);
        Room teknikringen = new Room(RoomDescription.TEKNIKRINGEN);
        Room brinellvagen = new Room(RoomDescription.BRINELLVAGEN);
        
        // initialise room exits
        bind(theaterD1,"north",theaterF1,"south");
        bind(theaterD1,"west",theaterE1,"east");
        bind(theaterF1,"north",theaterF2,"south");
        bind(theaterF1,"southwest",theaterE1,"northeast");
        bind(theaterF1,"west",library,"east");
        bind(theaterE1,"north",library,"south");
        bind(theaterE1,"west",infocenter,"east");
        bind(infocenter,"west",nymble,"east");
        bind(nymble,"north",escapen,"south");
        alley.setExit("west",escapen); //One-way road.
        bind(escapen,"northwest",lantmateri,"southeast");
        bind(alley,"east",library,"west");
        bind(library,"north",osquarsBacke,"south");
        bind(library,"north",teknikringen,"south");
        bind(teknikringen,"east",theaterK1,"west");
        bind(teknikringen,"northwest",hallV22,"southeast");
        bind(hallV22,"southwest",brinellvagen,"northeast");
        bind(hallV22,"northwest",hallKTH,"southeast");
        bind(brinellvagen,"southwest",lantmateri,"northeast");
        bind(lantmateri,"west",theaterQ1,"east");
        bind(hallKTH,"northwest",hallExam,"southeast");

        theaterE1.setFirstLecture(new Time(8,15),new Time(10,0),new Knowledge  (0,3,1,2,0),"Differentialer","E1");
        theaterF2.setFirstLecture(new Time(9,15),new Time(11,0),new Knowledge  (0,0,4,0,0),"Integralkalkyl","F2");
        theaterK1.setFirstLecture(new Time(9,15),new Time(11,0),new Knowledge  (0,0,0,0,0),"Kovalenta bindningar och redoxreaktioner","K1");
        theaterD1.setFirstLecture(new Time(10,15),new Time(12,0),new Knowledge (3,2,1,1,0),"Gränsvärden och differentialer","D1");
        hallV22.setFirstLecture(new Time(10,15),new Time(12,0),new Knowledge   (1,2,0,4,0),"Differentialekvationer och sån't","V22");
        theaterF1.setFirstLecture(new Time(10,15),new Time(12,0),new Knowledge (1,1,2,1,1),"Integralkalkyl och annat","F1");
        theaterE1.setSecondLecture(new Time(11,15),new Time(13,0),new Knowledge(1,0,0,0,5),"Taylorserier","E1");
        theaterQ1.setFirstLecture(new Time(11,15),new Time(13,0),new Knowledge (2,2,3,3,3),"Envarreföreläsning med Sveriges bästa föreläsare","Q1");
        theaterF2.setSecondLecture(new Time(11,15),new Time(13,0),new Knowledge(2,1,1,1,1),"Gränsvärden och annat","Q1");
        theaterK1.setSecondLecture(new Time(12,15),new Time(14,0),new Knowledge(0,0,0,0,0),"Titrering och biokemi","K1");
        theaterF1.setSecondLecture(new Time(12,15),new Time(14,0),new Knowledge(3,0,0,0,3),"Taylorserier och gränsvärden","F1");
        theaterD1.setSecondLecture(new Time(13,15),new Time(15,0),new Knowledge(2,3,0,3,0),"Extremvärden och differentialekvationer","D1");
        hallV22.setSecondLecture(new Time(14,15),new Time(16,0),new Knowledge  (0,2,2,1,0),"Integraler och derivata","V22");
        theaterQ1.setSecondLecture(new Time(14,15),new Time(16,0),new Knowledge(0,0,5,0,0),"Integralkalkyl","Q1");
        
        rooms.add(theaterD1);
        rooms.add(theaterE1);
        rooms.add(theaterF1);
        rooms.add(theaterF2);
        rooms.add(theaterK1);
        rooms.add(theaterQ1);
        rooms.add(hallV22);
        rooms.add(hallExam);
        rooms.add(hallKTH);
        rooms.add(library);
        rooms.add(infocenter);
        rooms.add(nymble);
        rooms.add(alley);
        rooms.add(lantmateri);
        rooms.add(osquarsBacke);
        rooms.add(teknikringen);
        rooms.add(brinellvagen);
        
        player.setCurrentRoom(theaterD1);
    }
    
    /**
     * Makes it possible to traverse two rooms.
     */
    private void bind(Room a,String aToB,Room b,String bToA){
        a.setExit(aToB,b);
        b.setExit(bToA,a);
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play(){            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (!finished){
            timer.displayCurrentTime();
            System.out.println();
            System.out.println();
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(player.getJustMoved()){
                timer.increaseMinute(5);
                player.setJustMoved(false);
            }
            player.updateCircadianRhythm(timer.getCurrentTime());
            if(timeIsUp()){
                finished = true;
            }
        }
        if(timeIsUp()){
            gameOverLate();
        }
        else{
            printGoodbye();
        }
    }
    
    /**
     * Prints out a good-bye message.
     */
    private void printGoodbye(){
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Prints out the opening message for the player.
     */
    private void printWelcome(){
        System.out.println();
        printHeader();
        System.out.println("Tentan is a new, incredibly fun adventure game.");
        System.out.println("Story:");
        System.out.println("You are a d-Osquarulda, and it is time for the final exam in Envariabelanalys.");
        System.out.println("As you have been recklessly procrastinating lately,");
        System.out.println("you are quite sure you will fail the test.");
        System.out.println("You have arrived at KTH campus. You need to exploit the few precious hours");
        System.out.println("left by gathering as much knowledge as you can before the exam.");
        System.out.println("Unfortunately, you forgot to bring both lunchbox and money.");
        System.out.println("You will have to find a way to get some food,");
        System.out.println("or do the exam on an empty stomach.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Prints out the title screen.
     */
    private void printHeader(){
        System.out.println("--- Tentan ---");
        System.out.println("Version 1.1");
    }
    
    /**
     * Prints out the game-over screen.
     */
    private void printGameOver(){
        System.out.println("--- GAME OVER ---");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command){
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN){
            System.out.println("I don't know what you mean...");
            return false;
        }

        switch(commandWord){
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case ENTER:
                event(command);
                break;
            case CHARGE:
                player.charge();
                break;
            case FIRE:
                player.fire();
                break;
            case QUIT:
                wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    /**
     * A certain event takes place if the player enters a room.
     * 
     * @param command The input command.
     */
    private void event(Command command){
        if(player.getRoomDescription() == RoomDescription.HALL_EXAM){
            enterExamHall(command);
        }else if(player.getRoomDescription() == RoomDescription.LANTMATERI){
            enterLantmateri();
        }else if(player.getRoomDescription() == RoomDescription.INFOCENTER){
            enterInfocenter();
        }else if(player.getRoomDescription() == RoomDescription.ESCAPEN){
            enterESCapen();
        }else if(player.getRoomDescription() == RoomDescription.NYMBLE){
            enterNymble();
        }else if(player.getRoomDescription() == RoomDescription.LIBRARY){
            enterLibrary(command);
        }else if(player.getRoomDescription() == RoomDescription.HALL_KTH){
            enterHallKTH(command);
        }else if(player.getCurrentRoom().hasLectureNow(timer.getCurrentTime())){
            enterLectureLocale(command);
        }else{
            System.out.println("There is nothing to enter right here and right now.");
        }
    }

    /**
     * The player entered a lecture theater.
     * 
     * @param command The input command.
     */
    private void enterLectureLocale(Command command){
        boolean answerIsValid = false;
        while(!answerIsValid){
            System.out.println("The lecture has started. Care to join?");
            command = parser.getCommand();
            CommandWord commandWord = command.getCommandWord();
            if(commandWord == CommandWord.YES){
                answerIsValid = true;
                player.learn(timer.getCurrentTime());
                System.out.println("You listen carefully to the lecturer.");
                if(player.getCurrentRoom().getOngoingLecture(timer.getCurrentTime()).getKnowledge().containsKnowledge()){
                    System.out.println("You expanded your knowledge in one-variable analysis!");
                }else{
                    System.out.println("You expanded your knowledge in chemistry!");
                    System.out.println("Sadly, you didn't get any better at one-variable analysis.");
                }
                timer.setCurrentTime(player.getCurrentRoom().getOngoingLecture(timer.getCurrentTime()).getEndTime());
                player.decreaseInsight(new Degree(2));
            }else if(commandWord == CommandWord.NO){
                answerIsValid = true;
                System.out.println("You leave.");
                timer.increaseMinute(2);
            }else{
                System.out.println("Yes or no?");
            }
        }
    }
    
    /**
     * The player entered the library.
     * 
     * @param command The input command.
     */
    private void enterLibrary(Command command){
        boolean answerIsValid = false;
        while(!answerIsValid){
            System.out.println("You are in the KTH Library.");
            System.out.println("This is an excellent place to further increase your understanding");
            System.out.println("of analysis in one variable and gather new insights.");
            System.out.println("Spend an hour in the library?");
            command = parser.getCommand();
            CommandWord commandWord = command.getCommandWord();
            if(commandWord == CommandWord.YES){
                answerIsValid = true;
                timer.increaseHour(1);
                player.setInsight(new Degree(10));
                System.out.println("You feel insightful!");
            }else if(commandWord == CommandWord.NO){
                answerIsValid = true;
                System.out.println("You leave.");
                timer.increaseMinute(2);
            }else{
                System.out.println("Yes or no?");
            }
        }
    }
    
    /**
     * The player entered the KTH Hall.
     * The player may choose to exercise in here
     * for a few hours. Essentially, exercising
     * is a waste of time.
     * 
     * @param command The input command.
     */
    private void enterHallKTH(Command command){
        boolean answerIsValid = false;
        while(!answerIsValid){
            System.out.println("You are in a gym.");
            System.out.println("Exercise for a few hours?");
            command = parser.getCommand();
            CommandWord commandWord = command.getCommandWord();
            if(commandWord == CommandWord.YES){
                answerIsValid = true;
                timer.increaseHour(3);
                System.out.println("You are exhausted but feel great.");
                System.out.println("You feel as if you have been wasting your time.");
            }else if(commandWord == CommandWord.NO){
                answerIsValid = true;
                System.out.println("You leave.");
                timer.increaseMinute(2);
            }else{
                System.out.println("Yes or no?");
            }
        }
    }
    
    /**
     * The player entered ESCapen.
     * The player will beg for food with a
     * certain success rate.
     */
    private void enterESCapen(){
        System.out.println("You notice one of your fellow students. You start begging for food.");
        if(Math.random() < 0.3){
            System.out.println("Sucess! You now carry a can of frozen beans!");
            System.out.println("The microwave ovens in ESCapen are preoccupied. You will have to");
            System.out.println("find some other way to unfreeze your food.");
            player.setCarriesLunch(true);
        }else{
            System.out.println("It failed. Perhaps if you try again...");
            System.out.println("You leave.");            
        }
        timer.increaseMinute(15);
    }
    
    /**
     * The player entered the Nymble.
     * If the player carries food, he can eat it in here
     * in order to gain maximum spryness.
     */
    private void enterNymble(){
        if(player.getCarriesLunch()){
            System.out.println("You unlock the door with your can of beans.");
            System.out.println("The microwave ovens in here are not quite as preoccupied.");
            System.out.println("You microwave your food and eat it.");
            System.out.println("You suddenly feel spry!");
            player.setCarriesLunch(false);
            player.setSpryness(new Degree(10));
            timer.increaseMinute(20);
        }
        else{
            System.out.println("The door to ESCapen is locked. For some reason, only food");
            System.out.println("can open the door.");
            System.out.println("You leave.");
            timer.increaseMinute(3);
        }
    }
    
    /**
     * The player has entered the KTH Information Center.
     * In here, the player will be given information on
     * today's lectures.
     */
    private void enterInfocenter(){
        System.out.println("The people at the information center are willing to");
        System.out.println("give you some relevant information:");
        printLectureInfo();
        timer.increaseMinute(4);
    }
    
    /**
     * Prints information on today's lecures.
     */
    private void printLectureInfo(){
        System.out.println("Today's lectures:");
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()){
            it.next().printLectureInfo();
        }
    }
    
    /**
     * The player has entered Lantmäteriet.
     * The player will be granted a map of campus,
     * as well as some explanations regarding the map.
     */
    private void enterLantmateri(){
        System.out.println("The workers at Lantmäteriet bestow you a");
        System.out.println("somewhat badly drawn map over campus:");
        printMap();
        System.out.println("L - You Are Here");
        System.out.println("M - Maskinparken");
        System.out.println("H - KTH-Hallen");
        System.out.println("V - V-building");
        System.out.println("K - K-building");
        System.out.println("E - E-building");
        System.out.println("D - D-building");
        System.out.println("F - F1-theater");
        System.out.println("G - F2-theater");
        System.out.println("Q - Q-building");
        System.out.println("C - ESCapen");
        System.out.println("B - Library");
        System.out.println("N - Nymble");
        System.out.println("I - KTH Infocenter");
        System.out.println("¤ - Uninteresting area");
        System.out.println("- / \\ | - Road");
        System.out.println("< - One-way road");
        System.out.println("# - Inaccessible");
        timer.increaseMinute(8);
    }
    
    /**
     * The player has entered the exam hall.
     * The player may choose to wait for the
     * exam to start, as long as it has not
     * started yet.
     */
    private void enterExamHall(Command command){
        boolean answerIsNo = false;
        while(!answerIsNo){
            System.out.println("The exam will be held in here.");
            System.out.println("Wait for the exam to start?");
            command = parser.getCommand();
            CommandWord commandWord = command.getCommandWord();
            if(commandWord == CommandWord.YES){
                doExam();
            }else if(commandWord == CommandWord.NO){
                answerIsNo = true;
            }else{
                System.out.println("Yes or no?");
            }
        }
    }
    
    /**
     * Prints a map of campus.
     */
    private void printMap(){
        System.out.println("  M        ");
        System.out.println("   \\       ");
        System.out.println("    H      ");
        System.out.println("     \\     ");
        System.out.println("      V    ");
        System.out.println("     / \\   ");
        System.out.println("    ¤   ¤-K");
        System.out.println("   /    |  ");
        System.out.println("Q-L     ¤ G");
        System.out.println("   \\    | |");
        System.out.println("    C-<-B-F");
        System.out.println("    |   |/|");
        System.out.println("    N-I-E-D");
    }

    /**
     * Prints out some help information.
     * Here we print some smart, cryptic message, a list of the 
     * command words and the player's stats.
     */
    private void printHelp(){
        System.out.println("You are depressed. You are suffering from exam anxiety.");
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println();
        System.out.println("The exam starts at 15:00 in Maskinparken.");
        System.out.println("In order to attend lectures, go to the place where the lecture is held");
        System.out.println("at the right time and enter the command 'enter'.");
        System.out.println("Stats:");
        System.out.println("Spryness: "+player.getSpryness().getValue());
        System.out.println("Insight: "+player.getInsight().getValue());
        System.out.println("Knowledge level in Gränsvärden: "+player.getKnowledge().getLimitCalculus().getValue());
        System.out.println("Knowledge level in Differentialkalkyl: "+player.getKnowledge().getDifferentialCalculus().getValue());
        System.out.println("Knowledge level in Integralkalkyl: "+player.getKnowledge().getIntegralCalculus().getValue());
        System.out.println("Knowledge level in Differentialekvationer: "+player.getKnowledge().getDifferentialEquations().getValue());
        System.out.println("Knowledge level in Taylorserier: "+player.getKnowledge().getTaylorSeries().getValue());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. You can also choose to
     * go nuts, which wastes time.
     * 
     * @param command The input command.
     */
    private void goRoom(Command command){
        if(!command.hasSecondWord()){
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        
        if(command.getSecondWord().equalsIgnoreCase("nuts")){
            int killedMinutes = 3;
            System.out.println("You go nuts. It takes "+killedMinutes+" minutes for you to come to your senses.");
            timer.increaseMinute(killedMinutes);
        }
        else if (nextRoom == null) {
            System.out.println("You can't go that way!");
        }
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoomLongDescription());
            player.setJustMoved(true);
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The input command.
     * @return True if this command quits the game, false otherwise.
     */
    private boolean quit(Command command){
        if(command.hasSecondWord()){
            System.out.println("Quit what?");
            return false;
        }
        else{
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * @return True if the exam has already started,
     * else false.
     */
    private boolean timeIsUp(){
        return timer.getCurrentHour() >= 15;
    }
    
    /**
     * The game is over because the player did not attend the exam in time.
     */
    private void gameOverLate(){
        System.out.println("The exam has already started. All is lost...");
        printGameOver();
    }
    
    /**
     * The game is over bacause the player failed the exam.
     */
    private void gameOverFail(){
        System.out.println("You failed the exam. All is lost...");
        printGameOver();
    }
    
    /**
     * @return The total game score.
     * The score depends on the player's spryness,
     * insight and knowledge.
     */
    private int getScore(){
        return player.getMerits()*player.getSpryness().getValue()*player.getInsight().getValue();
    }
    
    /**
     * @return The players grade in the form of a String. The grade can be either:
     * A, B, C, D, E or F.
     */
    private String getGrade(){
        if(getScore() >= 3*PASS_LIMIT){
            return "A";
        }else if(getScore() >= 2.5*PASS_LIMIT){
            return "B";
        }else if(getScore() >= 2*PASS_LIMIT){
            return "C";
        }else if(getScore() >= 1.5*PASS_LIMIT){
            return "D";
        }else if(getScore() >= PASS_LIMIT){
            return "E";
        }else{
            return "F";
        }
    }
    
    /**
     * Prints out an ending screen.
     */
    private void victory(){
        System.out.println("You passed the exam!");
        System.out.println("Congratulations! You have completed a great game");
        System.out.println("and proved the justice of our culture.");
        System.out.println("Now, get back to slacking.");
        System.out.println("--- THE END ---");
    }
    
    /**
     * The player does the exam. Determines the grade.
     */
    private void doExam(){
        System.out.println("You wait for the exam to start.");
        System.out.println("You do the exam.");
        System.out.println("Weeks pass. Eventually, your exam result is published.");
        System.out.println("Your grade is...: "+getGrade()+" !");
        if(getScore() > PASS_LIMIT){
            victory();
        }else{
            gameOverFail();
        }
        System.exit(0); //Ends the game.
    }
}
