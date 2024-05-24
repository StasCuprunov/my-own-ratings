import {setIdAttribute, setNumberAttribute} from "../utility/ClassUtility";

export class RangeOfValues {
    id: string | null;
    minimum: number;
    maximum: number;
    stepWidth: number;

    constructor(id?: string, minimum?: number, maximum?: number, stepWidth?: number) {
        this.id = setIdAttribute(id);
        this.minimum = setNumberAttribute(minimum);
        this.maximum = setNumberAttribute(maximum);
        this.stepWidth = setNumberAttribute(stepWidth);
    }
}