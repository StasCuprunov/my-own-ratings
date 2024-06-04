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

export const RatingFormPage: FunctionComponent<any> = ({props}) => {
    const isEdit: boolean = props.isEdit;

    if (props.backendError) {
        return (
            <Error error={props.backendError}/>
        );
    }

    return (
        <div>
            <h1>{props.title}</h1>
            <form onSubmit={props.handleSubmit}>
                <FormForInput props={props.formForName}/>
                <FormForTextArea props={props.formForDescription}/>
                <div>
                    {getScaleTitle(isEdit)}
                    <FormForNumber props={props.formForMinimum}/>
                    <FormForNumber props={props.formForMaximum}/>
                    <FormForNumber props={props.formForStepWidth}/>
                    <InputError props={props.scaleValidation}/>
                </div>
                {getButtons(isEdit, props.id)}
                <div>
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
            </form>
        </div>
    );
};