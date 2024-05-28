import {FunctionComponent} from "react";
import {RatingPage} from "./RatingPage";
import {getCreateRatingEntryButtonObject, getDeleteRatingButtonObject} from "./RatingFunctions";

export const Rating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;
    const deleteRatingButton: any = getDeleteRatingButtonObject(id);
    const createRatingEntryButton: any = getCreateRatingEntryButtonObject(id);

    return (
        <RatingPage id={id} name={props.name} description={props.description}
                    rangeOfValues={props.rangeOfValues} ratingEntries={props.ratingEntries}
                    deleteRatingButton={deleteRatingButton} createRatingEntryButton={createRatingEntryButton}
        />
    );
};