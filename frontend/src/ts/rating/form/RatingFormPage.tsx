import {FunctionComponent} from "react";
import {Error} from "../../general-page/error/Error";
import {Label} from "../../component/atom/form/Label";
import {Input} from "../../component/atom/form/input/Input";
import {InputError} from "../../component/atom/form/input/InputError";
import {TextArea} from "../../component/atom/form/TextArea";
import {InputNumber} from "../../component/atom/form/input/InputNumber";
import {CreateButton} from "../../component/atom/button/CreateButton";
import {
    getHintAdaptionRatingEntries,
    getHintMaximumDecimalPlaces,
    getHintRatingNameMustBeUnique,
    getHintRequired,
    getHintWhatIsAScale
} from "./RatingHints";
import {
    getLabelDescriptionProps,
    getLabelMaximumProps,
    getLabelMinimumProps,
    getLabelNameProps,
    getLabelStepWidthProps,
    getScaleTitle
} from "./RatingFormFunctions";

const labelName: any = getLabelNameProps();
const labelDescription: any = getLabelDescriptionProps();
const labelMinimum: any = getLabelMinimumProps();
const labelMaximum: any = getLabelMaximumProps();
const labelStepWidth: any = getLabelStepWidthProps();

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
                <div>
                    <Label props={labelName}/>
                    <Input props={props.inputName}/>
                    <InputError props={props.nameValidation}/>
                </div>
                <div>
                    <Label props={labelDescription}/>
                    <TextArea props={props.textAreaDescription}/>
                </div>
                <div>
                    {getScaleTitle(isEdit)}
                    <div>
                        <Label props={labelMinimum}/>
                        <InputNumber props={props.inputMinimum}/>
                        <InputError props={props.minimumValidation}/>
                    </div>
                    <div>
                        <Label props={labelMaximum}/>
                        <InputNumber props={props.inputMaximum}/>
                        <InputError props={props.maximumValidation}/>
                    </div>
                    <div>
                        <Label props={labelStepWidth}/>
                        <InputNumber props={props.inputStepWidth}/>
                    </div>
                    <InputError props={props.scaleValidation}/>
                </div>
                <div>
                    {!isEdit &&
                        <CreateButton/>
                    }
                    {isEdit &&
                        <div>Buttons for edit</div>
                    }
                </div>
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