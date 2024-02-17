public class Main {
    public static void main(String[] args) {
        LaunchPage launchPage = new LaunchPage();

        launchPage.getFrame().getStartButton().addActionListener(e -> {
            System.out.println("Start button clicked");
        });
        launchPage.getFrame().getInstructionsButton().addActionListener(e -> {
            launchPage.getFrame().dispose();
            InstructionsPage instructionsPage = new InstructionsPage();
            instructionsPage.getFrame().getBackButton().addActionListener(e1 -> {
                instructionsPage.getFrame().dispose();
                launchPage.getFrame().setVisible(true);
            });
        });
    }
}
