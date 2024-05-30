import {setIdAttribute, setNumberAttribute, setStringAttribute} from "../utility/ClassUtility";

export class RatingEntry {
    id: string | null;
    ratingId: string;
    name: string;
    value: number;

    constructor(id?: string, ratingId?: string, name?: string, value?: number) {
        this.id = setIdAttribute(id);
        this.ratingId = setStringAttribute(ratingId);
        this.name = setStringAttribute(name);
        this.value = setNumberAttribute(value);
    }
}