import {isLastIndex} from "./MathUtility";

export const getClassNameAttribute = (classNames: string[]): string => {
    let classNameAttribute: string = "";
    let length: number = classNames.length;

    for (let index: number = 0; index < length; index++) {
        classNameAttribute += classNames[index];

        if (!isLastIndex(index, length)) {
            classNameAttribute += " ";
        }
    }
    return classNameAttribute;
};