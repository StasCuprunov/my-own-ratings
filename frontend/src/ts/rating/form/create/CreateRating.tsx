import {FunctionComponent} from "react";
import {RatingForm} from "../RatingForm";

export const CreateRating: FunctionComponent<any> = ({props}) => {

    const ratingFormProps: any = {
        ...props,
        isEdit: false
    };

    return (
        <RatingForm props={ratingFormProps}/>
    );
};