import {FunctionComponent} from "react";
import {RatingForm} from "../RatingForm";

export const CreateRating: FunctionComponent<any> = ({props}) => {

    const ratingFormProps: any = {
        ...props,
        isEdit: false,
        title: "Create your own rating"
    };

    return (
        <RatingForm props={ratingFormProps}/>
    );
};