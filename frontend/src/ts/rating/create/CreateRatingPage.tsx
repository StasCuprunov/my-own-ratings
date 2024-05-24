import {FunctionComponent} from "react";
import {Label} from "../../component/atom/form/Label";
import {Input} from "../../component/atom/form/input/Input";
import {InputNumber} from "../../component/atom/form/input/InputNumber";
import {
    getLabelDescriptionProps,
    getLabelMaximumProps,
    getLabelMinimumProps,
    getLabelNameProps, getLabelStepWidthProps
} from "./CreateRatingFunctions";
import {TextArea} from "../../component/atom/form/TextArea";
import {InputError} from "../../component/atom/form/input/InputError";
import {CreateButton} from "../../component/atom/button/CreateButton";
import {ErrorPage} from "../../general-page/ErrorPage";

const labelName: any = getLabelNameProps();
const labelDescription: any = getLabelDescriptionProps();
const labelMinimum: any = getLabelMinimumProps();
const labelMaximum: any = getLabelMaximumProps();
const labelStepWidth: any = getLabelStepWidthProps();

export const CreateRatingPage: FunctionComponent<any> = ({maximumNumberOfDecimalDigits, inputName, textAreaDescription,
                                                             inputMinimum, inputMaximum, inputStepWidth, handleSubmit,
                                                             nameValidation, minimumValidation, maximumValidation,
                                                             scaleValidation, backendError}) => {
    if (backendError) {
        return (
            <ErrorPage error={backendError}/>
        );
    }

    return (
        <div>
            <h1>Create your own rating</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Label props={labelName}/>
                    <Input props={inputName}/>
                    <InputError props={nameValidation}/>
                </div>
                <div>
                    <Label props={labelDescription}/>
                    <TextArea props={textAreaDescription}/>
                </div>
                <div>
                    <h2>Scale<sup>3, 4</sup></h2>
                    <div>
                        <Label props={labelMinimum}/>
                        <InputNumber props={inputMinimum}/>
                        <InputError props={minimumValidation}/>
                    </div>
                    <div>
                        <Label props={labelMaximum}/>
                        <InputNumber props={inputMaximum}/>
                        <InputError props={maximumValidation}/>
                    </div>
                    <div>
                        <Label props={labelStepWidth}/>
                        <InputNumber props={inputStepWidth}/>
                    </div>
                    <InputError props={scaleValidation}/>
                </div>
                <div>
                    <CreateButton/>
                </div>
                <div>
                    <h2>Hints</h2>
                    <ul>
                        <li>
                            <sup>1</sup>Required
                        </li>
                        <li>
                            <sup>2</sup>Has to be unique for every rating
                        </li>
                        <li>
                            <sup>3</sup>A scale has a minimum, maximum and other values which are in the scale.
                            Every value excludes the minimum and maximum has a previous and following value with the
                            same distance to each other which is described by the step width.
                        </li>
                        <li>
                            <sup>4</sup>Every attribute may have maximum {maximumNumberOfDecimalDigits} decimal places.
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
};