package by.bsu.zmicier.meta.game.player.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;

import java.util.List;
import java.util.Scanner;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class HumanPlayer<M, P extends MetaPosition<P>> extends AbstractPlayer<M, P> {
    @Override
    public M move(P position, GameManagerClient<M, ?, P> client) {
        System.out.println(position.toString());
        System.out.println("Possible moves: \n");

        List<M> moves = client.getMyMoves(position);
        int i = 0;
        for(M move: moves) {
            System.out.println(i + " --> " + move);
            i++;
        }

        System.out.println("Your move?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            if(!sc.hasNextInt()) {
                sc.next();
                continue;
            }
            Integer mov = sc.nextInt();
            if(mov >= 0 && mov < moves.size()) {
                return moves.get(mov);
            }
        }
    }
}
