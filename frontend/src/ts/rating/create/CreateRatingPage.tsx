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

const labelName: any = getLabelNameProps();
const labelDescription: any = getLabelDescriptionProps();
const labelMinimum: any = getLabelMinimumProps();
const labelMaximum: any = getLabelMaximumProps();
const labelStepWidth: any = getLabelStepWidthProps();

export const CreateRatingPage: FunctionComponent<any> = ({inputName, textAreaDescription, inputMinimum, inputMaximum,
                                                             inputStepWidth, handleSubmit, minimumValidation,
                                                             maximumValidation, stepWidthValidation}) => {
    return (
        <div>
            <h1>Create your own rating</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Label props={labelName}/>
                    <Input props={inputName}/>
                </div>
                <div>
                    <Label props={labelDescription}/>
                    <TextArea props={textAreaDescription}/>
                </div>
                <div>
                    <h2>Scale<sup>3</sup></h2>
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
                        <InputError props={stepWidthValidation}/>
                    </div>
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
                            <sup>2</sup>Unique for every rating
                        </li>
                        <li>
                            <sup>3</sup>...
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
};