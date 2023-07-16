public class ValidPalindrome {
    public static void main(String[] args) throws Exception {
        String testCase1 = "A man, a plan, a canal: Panama";
        String testCase2 = "race a car";
        isValid(testCase1);
        isValid(testCase2);
        isPalindrome(testCase1);

    }

    private static boolean isValid(String testCase) {
        String result = testCase.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(result);
        String resulString = "";
        for (int i = 0; i < result.length(); i++) {
            char ch = result.charAt(i);
            resulString = ch + resulString;
        }
        boolean resultBool = result.equalsIgnoreCase(resulString);
        System.out.println(resultBool);
        return resultBool;
    }

    private static boolean isPalindrome(String s) {
        String result = s.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(result);
        String resulString = new StringBuffer(result).reverse().toString();
        boolean resultBool = result.equalsIgnoreCase(resulString);
        System.out.println(resultBool);
        return resultBool;
    }
}
