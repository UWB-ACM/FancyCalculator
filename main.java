import java.util.*;
import java.io.*;

class main {

    private static ArrayList<String> run(String cmd) throws Exception {
        ProcessBuilder builder = new ProcessBuilder( "cmd.exe", "/c", cmd);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

        ArrayList<String> result = new ArrayList<String>();
        String line;
        while ((line = r.readLine()) != null) {
            System.out.println(">>> HUB: " + line);
            result.add(line);
        }
        return result;
    }

    public static void main(String ... args) throws Exception {

        // Get list of all open issues
        ArrayList<String> result = run("hub issue");

        for (String s : result) {
            // Close all of the issues
            // instead of this hack you can use format %i = https://hub.github.com/hub-issue.1.html
            String id = s.trim().substring(1).split(" ")[0];
            if (id.equals("60")) {
                continue;
            }

            System.out.println(">>> closing issue = " + id);
            run("hub issue update " + id + " -s closed");
        }

        // Read the list of issues that need to be created
        for (int i = 0; i < 60; i++) {
            CreateFile(i+1);
            run("hub issue create -l easy -F issue_template");
        }
    }

    private static void CreateFile(int n) throws Exception {
        PrintWriter pw = new PrintWriter("issue_template");
        pw.println("Add theme " + n +  " for the calculator");
        pw.println("");
        pw.println("Requirement: Create a new template");
        pw.println("");
        pw.println("Open `calculators.js` find line "+ (201 + ((n-1)*6)) +" and find this code:");
        pw.println("```js");
        pw.println("    // TODO: Add theme " + n + " - To fix this:");
        pw.println("    //   - Change \"theme name\" to a catchy name (use your name or alias)");
        pw.println("    //   - Change the colors blue and red with colors of your choice.");
        pw.println("    //   - Uncomment the call to addOption (remove the `//`)");
        pw.println("    // addOption(\"theme name\", \"linear-gradient(blue, red)\", \"red\", \"red\");");
        pw.println("```");
        pw.println("");
        pw.println("As the comment suggests:");
        pw.println("  * Change the theme name to something meaningful");
        pw.println("  * Change the colors to those of your liking");
        pw.println("");
        pw.println("Final result should look like this:");
        pw.println("");
        pw.println("```js");
        pw.println("    // TODO: Add theme " + n + " - To fix this:");
        pw.println("    //   - Change \"theme name\" to a catchy name (use your name or alias)");
        pw.println("    //   - Change the colors blue and red with colors of your choice.");
        pw.println("    //   - Uncomment the call to addOption (remove the `//`)");
        pw.println("    addOption(\"alango theme\", \"linear-gradient(red, green)\", \"blue\", \"white\");");
        pw.println("```");
        pw.close();
    }
}
