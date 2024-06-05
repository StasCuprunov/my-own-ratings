import {FunctionComponent} from "react";
import {Error} from "../../general-page/error/Error";
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
import {
    CSS_CLASS_CONTAINER,
    CSS_CLASS_FORM_GROUP,
    CSS_CLASS_FORM_RATING,
    CSS_CLASS_HINTS,
    CSS_CLASS_SCALE,
    CSS_CLASS_WEBSITE_CONTAINER
} from "../../constant/CSSClassNameConstants";
import {getClassNameAttribute} from "../../utility/CSSUtility";

export const RatingFormPage: FunctionComponent<any> = ({props}) => {
    const isEdit: boolean = props.isEdit;

    if (props.backendError) {
        return (
            <Error error={props.backendError}/>
        );
    }

    return (
        <div className={getClassNameAttribute([CSS_CLASS_WEBSITE_CONTAINER, CSS_CLASS_FORM_RATING])}>
            <h1>{props.title}</h1>
            <div className={CSS_CLASS_CONTAINER}>
                <form onSubmit={props.handleSubmit} autoComplete={"off"}>
                    <div className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_FORM_GROUP])}>
                        <FormForInput props={props.formForName}/>
                        <FormForTextArea props={props.formForDescription}/>
                    </div>
                    <div className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_SCALE])}>
                        {getScaleTitle(isEdit)}
                        <div className={CSS_CLASS_FORM_GROUP}>
                            <FormForNumber props={props.formForMinimum}/>
                            <FormForNumber props={props.formForMaximum}/>
                            <FormForNumber props={props.formForStepWidth}/>
                        </div>
                    </div>
                    {getButtons(isEdit, props.id)}
                </form>
            </div>
            <div className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_HINTS])}>
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