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
        let passwordErrors: string[] = [];
        let isPasswordValid: boolean = true;
        if (!this.atLeastOneDigitRegExp.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one digit");
        }
        if (!this.atLeastOneEnglishUpperCaseLetterRegExp.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one English uppercase letter");
        }
        if (!this.atLeastOneEnglishLowerCaseLetterRegExp.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one English lowercase letter");
        }
        if(!this.atLeastOneValidSpecialCharacterRegExp.test(password)) {
            isPasswordValid = false;
            passwordErrors.push("Your password needs minimum one of the following special characters: " +
                this.enumerationOfValidSpecialCharacters);
        }
        return {
            isPasswordValid: isPasswordValid,
            passwordErrors: passwordErrors
        }
    };
}