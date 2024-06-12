import {RatingEntry} from "./RatingEntry";
import {setFunctionAttribute} from "../../utility/ClassUtility";

export class RatingEntryForDataGrid {
    ratingEntry: RatingEntry;
    handleEditClick: Function;
    handleDeleteClick: Function;

    constructor(ratingEntry?: RatingEntry, handleEditClick?: Function, handleDeleteClick?: Function) {
        this.ratingEntry = (ratingEntry) ? ratingEntry : new RatingEntry();
        this.handleEditClick = setFunctionAttribute(handleEditClick);
        this.handleDeleteClick = setFunctionAttribute(handleDeleteClick);
    }
}