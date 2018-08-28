/**
 * @author Khen Aharon
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import biuoop.GUI;
/**
 * class Ass5Game runs the game.
 */
public class Ass5Game {
    /**
     * @param args levels from command line.
     */
    public static void main(String[] args) {
        final int boardWidth = 800;
        final int boardHeight = 600;
        List<LevelInformation> levels = new LinkedList<LevelInformation>();
        LevelInformation [] nameLevels = new LevelInformation [4];
        nameLevels[3] = new Level4();
        nameLevels[2] = new Level3();
        nameLevels[1] = new Level2();
        nameLevels[0] = new Level1();
        if (args.length == 0) {
            for (int i = 0; i < 4; i++) {
                levels.add(nameLevels[i]);
            }
        } else  {
            levels = checkIfFound(args);
            if (levels.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    levels.add(nameLevels[i]);
                }
            }
        }
        GUI gui = new GUI("The sun shines", boardWidth, boardHeight);
        AnimationRunner runner = new AnimationRunner(50, gui);
        GameFlow gameflow = new GameFlow(runner,
                gui.getKeyboardSensor());
        gameflow.runLevels(levels);
        System.exit(0);
    }
    /**
     * Sets a values map according to the appropriate value.
     * @param command the entered command.
     * @return appropriate levels list.
     */
    public static List<LevelInformation> checkIfFound(String[] command) {
        List<LevelInformation> levels = new LinkedList<LevelInformation>();
        Map<String, LevelInformation> assignment = new TreeMap<String,
                LevelInformation>();
        assignment.put("4", new Level4());
        assignment.put("3", new Level3());
        assignment.put("2", new Level2());
        assignment.put("1", new Level1());
        for (String s : command) {
            if (assignment.containsKey(s)) {
                levels.add(assignment.get(s));
            }
        }
        return levels;
    }
}
