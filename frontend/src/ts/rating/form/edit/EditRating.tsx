import {FunctionComponent} from "react";
import {RatingForm} from "../RatingForm";

export const EditRating: FunctionComponent<any> = ({props}) => {

    const ratingFormProps: any = {
        ...props,
        isEdit: true
    };

    return (
        <RatingForm props={ratingFormProps}/>
    );
};