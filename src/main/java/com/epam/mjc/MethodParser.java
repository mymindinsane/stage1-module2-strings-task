package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String methodName = signatureString.substring(0, signatureString.indexOf("("));
        String arguments = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.lastIndexOf(")"));
        String[] argumentsArray = arguments.split(",");
        List<MethodSignature.Argument> listOfArgument = new ArrayList<>();
        if (!arguments.isEmpty()) {
            for (int i = 0; i < argumentsArray.length; i++) {
                StringTokenizer tokenizer = new StringTokenizer(argumentsArray[i], " ", false);
                listOfArgument.add(new MethodSignature.Argument(tokenizer.nextToken(), tokenizer.nextToken()));
            }
        }
        String[] methodNameArray = methodName.split(" ");
        MethodSignature methodSignature = new MethodSignature(methodNameArray[methodNameArray.length - 1], listOfArgument);
        if (methodNameArray.length == 3) {
            methodSignature.setAccessModifier(methodNameArray[0]);
            methodSignature.setReturnType(methodNameArray[1]);
        } else {
            methodSignature.setReturnType(methodNameArray[0]);
        }
        return methodSignature;
    }
}
