import {RangeOfValues} from "../model/RangeOfValues";
import {setIdAttribute, setStringAttribute} from "../utility/ClassUtility";

export class RatingDTO {
    id: string | null;
    userId: string;
    name: string;
    description: string;
    rangeOfValues: RangeOfValues;

    constructor(id?: string, userId?: string, name?: string, description?: string, rangeOfValues?: RangeOfValues) {
        this.id = setIdAttribute(id);
        this.userId = setStringAttribute(userId);
        this.name = setStringAttribute(name);
        this.description = setStringAttribute(description);
        this.rangeOfValues = (rangeOfValues == null) ? new RangeOfValues() : rangeOfValues;
    }
}