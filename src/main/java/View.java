public class View {
    /**
     * Выводит название
     */
    public static void printTitle() {
        System.out.println(new StringBuilder()
                .append("-------------------------------------------------\n")
                .append("          БЕСГРАМАТНЫЙ КАЛЬКУЛЯТАР       \n")
                .append("(я ни умею четать и счетать и вапще меня скатали)\n")
                .append("-------------------------------------------------")
        );
    }

    public static void printReadyToWrite() {
        switch ((int) Math.round(Math.random() * 2)) {
            case 0:
                System.out.println("---Пеши пример:");
                break;
            case 1:
                System.out.println("--Што счетать:");
                break;
            case 2:
                System.out.println("--Ввади:");
                break;
        }
    }
}
