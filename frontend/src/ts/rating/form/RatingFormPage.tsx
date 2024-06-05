import {FunctionComponent} from "react";
import {Error} from "../../general-page/error/Error";
import {InputError} from "../../component/atom/form/input/InputError";
import {
    getHintAdaptionRatingEntries,
    getHintMaximumDecimalPlaces,
    getHintRatingNameMustBeUnique,
    getHintRequired,
    getHintWhatIsAScale
} from "./RatingHints";
import {
    getButtons,
    getScaleTitle
} from "./RatingFormFunctions";
import {FormForTextArea} from "../../component/molecule/form-attribute/FormForTextArea";
import {FormForNumber} from "../../component/molecule/form-attribute/FormForNumber";
import {FormForInput} from "../../component/molecule/form-attribute/FormForInput";
import {CSS_CLASS_CONTAINER, CSS_CLASS_WEBSITE_CONTAINER} from "../../constant/CSSClassNameConstants";

export const RatingFormPage: FunctionComponent<any> = ({props}) => {
    const isEdit: boolean = props.isEdit;

    if (props.backendError) {
        return (
            <Error error={props.backendError}/>
        );
    }

    return (
        <div className={CSS_CLASS_WEBSITE_CONTAINER}>
            <h1>{props.title}</h1>
            <div className={CSS_CLASS_CONTAINER}>
                <form onSubmit={props.handleSubmit}>
                    <FormForInput props={props.formForName}/>
                    <FormForTextArea props={props.formForDescription}/>
                    <div className={CSS_CLASS_CONTAINER}>
                        {getScaleTitle(isEdit)}
                        <FormForNumber props={props.formForMinimum}/>
                        <FormForNumber props={props.formForMaximum}/>
                        <FormForNumber props={props.formForStepWidth}/>
                        <InputError props={props.scaleValidation}/>
                    </div>
                    {getButtons(isEdit, props.id)}
                </form>
            </div>
            <div className={CSS_CLASS_CONTAINER}>
                <h2>Hints</h2>
                <ul>
                    {getHintRequired()}
                    {getHintRatingNameMustBeUnique()}
                    {getHintWhatIsAScale()}
                    {getHintMaximumDecimalPlaces(props.maximumNumberOfDecimalDigits)}
                    {isEdit &&
                        getHintAdaptionRatingEntries()
                    }
                </ul>
            </div>
        </div>
    );
};