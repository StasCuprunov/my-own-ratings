import {RatingEntry} from "../../../../../model/RatingEntry";
import {getInputNumberProps} from "../../../../../utility/FormUtility";
import {
    API_ROUTING_RATING_ENTRIES_CREATE,
    API_ROUTING_RATING_ENTRIES_EDIT
} from "../../../../../constant/routing/APIRoutingConstants";
import {postAxios, putAxios} from "../../../../../interface/BackendCalls";

export const createRatingEntry = async (ratingEntry: RatingEntry) => {
    return await postAxios(API_ROUTING_RATING_ENTRIES_CREATE, ratingEntry);
};

export const editRatingEntry = async (ratingEntry: RatingEntry) => {
    return await putAxios(API_ROUTING_RATING_ENTRIES_EDIT, ratingEntry);
};

export const getLabelNameProps = (): any => {
    return {
        htmlFor: "name",
        text: "Name",
        sup: "1, 2"
    };
};

export const getLabelValueProps = (): any => {
    return {
        htmlFor: "value",
        text: "Value",
        sup: "1, 3"
    };
};

export const getInputValueProps = (min: number, max: number, step: number, value: number, handleChange: any): any => {
    return getInputNumberProps("value", true, min, max, step, value, handleChange);
};

export const hasAlreadyRatingEntryWithName = (name: string, ratingEntries: RatingEntry[],
                                              id: string | null): boolean => {
    let foundRatingEntries: RatingEntry[] = ratingEntries.filter(entry => entry.name === name);

    if (foundRatingEntries.length > 0) {
        if (id) {
            return foundRatingEntries[0].id !== id;
        }
        return true;
    }
    return false;
};