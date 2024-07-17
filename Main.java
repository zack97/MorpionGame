package MorpionGame;

import java.util.HashMap;
import java.util.Scanner;

import static MorpionGame.StringConstant.BLANK;


public class Main {
    public static void main(String[] args){
        final var game = new TicTacToe();

        var player = Player.FIRST;
        var players = initPlayers();

        while(true){
            try{
                System.out.println(game);
                System.out.println(players.get(player) + " / Saisissez un nombre entre 1 et 9 :");
                final var inputUser = getInputUser();

                game.processInput(player, inputUser);
                if(game.checkWin()){
                    System.out.println(game);
                    System.out.println("Le joueur " + players.get(player) + "a gagné la partie");
                    break;
                };

                if(game.checkDraw()){
                    System.out.println(game);
                    System.out.println("Personne n'a gagné la partie");
                    break;
                }

                player = nextPlayer(player);
            } catch (TicTacToeInvalidException e){
                System.out.println(e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("Vous devez saisir un chiffre");
            }

        }
    }

    private static HashMap<Player, String> initPlayers() {
        var players = new HashMap<Player, String>();
        var scanner = new Scanner(System.in);
        do{
            System.out.println("Saisissez le nom du joueur 1 : ");
            players.put(Player.FIRST, scanner.nextLine());

        }while (players.get(Player.FIRST).equals(BLANK));
        do{
            System.out.println("Saisissez le nom du joueur 2 : ");
            players.put(Player.SECOND, scanner.nextLine());

        }while (players.get(Player.SECOND).equals(BLANK));
        return players;
    }

    private static int getInputUser() throws TicTacToeInvalidException {
        final var scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        if(input.equals("exit") || input.equals("quitte")){
            System.exit(0);
        }
        var inputEntier = Integer.parseInt(input);
        if(inputEntier < 1 || inputEntier > 9){
            throw new TicTacToeInvalidException("Le chiffre doit etre entre 1 et 9");
        }
        return inputEntier;
    }

    private static Player nextPlayer(Player player) {
        if(player == Player.FIRST){
            return Player.SECOND;
        }else{
            return Player.FIRST;
        }
    }
}
