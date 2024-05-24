import {setBooleanAttribute, setStringAttribute} from "../utility/ClassUtility";

export class InputValidation {
    condition: boolean;
    text: string;

    constructor(condition?: boolean, text?: string) {
        this.condition = setBooleanAttribute(condition);
        this.text = setStringAttribute(text);
    }
}