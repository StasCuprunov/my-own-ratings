import {isLastIndex} from "../utility/MathUtility";

export class PasswordRegex {
    private atLeastOneDigitRegExp: RegExp;
    private atLeastOneEnglishUpperCaseLetterRegExp: RegExp;
    private atLeastOneEnglishLowerCaseLetterRegExp: RegExp;
    private atLeastOneValidSpecialCharacterRegExp: RegExp;
    private enumerationOfValidSpecialCharacters: string[];

    constructor(atLeastOneDigitRegex: string, atLeastOneEnglishUpperCaseLetterRegex: string,
                atLeastOneEnglishLowerCaseLetterRegex: string, atLeastOneValidSpecialCharacterRegex: string,
                enumerationOfValidSpecialCharacters: string[]) {
        this.atLeastOneDigitRegExp = new RegExp(atLeastOneDigitRegex);
        this.atLeastOneEnglishUpperCaseLetterRegExp = new RegExp(atLeastOneEnglishUpperCaseLetterRegex);
        this.atLeastOneEnglishLowerCaseLetterRegExp = new RegExp(atLeastOneEnglishLowerCaseLetterRegex);
        this.atLeastOneValidSpecialCharacterRegExp = new RegExp(atLeastOneValidSpecialCharacterRegex);
        this.enumerationOfValidSpecialCharacters = enumerationOfValidSpecialCharacters;
    }

    public checkPassword = (password: string): any => {
        let isPasswordValid: boolean = true;
        let textMessages: string[] = [];
        if (!this.atLeastOneDigitRegExp.test(password)) {
            isPasswordValid = false;
            textMessages.push("minimum one digit");
        }
        if (!this.atLeastOneEnglishUpperCaseLetterRegExp.test(password)) {
            isPasswordValid = false;
            textMessages.push("minimum one English uppercase letter");
        }
        if (!this.atLeastOneEnglishLowerCaseLetterRegExp.test(password)) {
            isPasswordValid = false;
            textMessages.push("minimum one English lowercase letter");
        }
        if(!this.atLeastOneValidSpecialCharacterRegExp.test(password)) {
            isPasswordValid = false;
            textMessages.push("minimum one of the following special characters: " +
                this.enumerationOfValidSpecialCharacters);
        }
        return {
            isPasswordValid: isPasswordValid,
            passwordErrorText: this.getText(textMessages)
        }
    };

    private getText = (textMessages: string[]): string => {
        let text: string = "Your password needs ";
        let length: number = textMessages.length;
        for (let index = 0; index < length; index++) {
            text += textMessages[index];
            if (!isLastIndex(index, length)) {
                text += ", ";
            }
        }
        return text;
    };
}