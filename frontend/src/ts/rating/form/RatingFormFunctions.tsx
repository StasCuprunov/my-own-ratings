import {RangeOfValues} from "../../model/RangeOfValues";
import {CreateButton} from "../../component/atom/button/CreateButton";
import {EditButton} from "../../component/atom/button/EditButton";
import {CancelButtonLink} from "../../component/atom/button/link/CancelButtonLink";
import {getWebsiteRoutingRatingsById, WEBSITE_ROUTING_RATINGS} from "../../constant/routing/WebsiteRoutingConstants";
import {getInputNumberProps, getInputTextProps} from "../../utility/FormUtility";
import {CSS_CLASS_BUTTON_GROUP} from "../../constant/CSSClassNameConstants";

export const getLabelNameProps = (): any => {
    return {
        htmlFor: "name",
        text: "Name",
        sup: "1, 2"
    };
};
export const getLabelDescriptionProps = (): any => {
    return {
        htmlFor: "description",
        text: "Description"
    };
};
export const getLabelMinimumProps = (): any => {
    return {
        htmlFor: "minimum",
        text: "Minimum",
        sup: "1"
    };
};
export const getLabelMaximumProps = (): any => {
    return {
        htmlFor: "maximum",
        text: "Maximum",
        sup: "1"
    };
};
export const getLabelStepWidthProps = (): any => {
    return {
        htmlFor: "step-width",
        text: "Step width",
        sup: "1"
    };
};
export const getInputNameProps = (value: string, maxLength: number, handleChange: any, handleBlur: any): any => {
    return getInputTextProps("name", value, maxLength, handleChange, handleBlur);
};
export const getTextAreaDescription = (value: string, maxLength: number, handleChange: any): any => {
    return {
        name: "description",
        maxLength: maxLength,
        value: value,
        onChange: handleChange
    }
};
export const getDefaultRangeOfValues = (): RangeOfValues => {
    return new RangeOfValues(undefined, 0, 5, 0.5);
};
export const getInputMinimum = (minFromInput: number, maxFromInput: number, stepFromInput: number,
                                value: number, handleChange: any, handleBlur: any): any => {
    return getInputNumberProps("minimum", true, minFromInput, maxFromInput, stepFromInput, value,
        handleChange, handleBlur);
};
export const getInputMaximum = (minFromInput: number, maxFromInput: number, stepFromInput: number, value: number,
                                handleChange: any, handleBlur: any): any => {
    return getInputNumberProps("maximum", true, minFromInput, maxFromInput, stepFromInput, value,
        handleChange, handleBlur);
};
export const getInputStepWidth = (minFromInput: number, maxFromInput: number, stepFromInput: number, value: number,
                                  handleChange: any, handleBlur: any): any => {
    return getInputNumberProps("step-width", true, minFromInput, maxFromInput, stepFromInput, value,
        handleChange, handleBlur);
};

export const getScaleTitle = (isEdit: boolean) => {
    let hintList: string = "3, 4";
    if (isEdit) {
        hintList += ", 5";
    }
    return (
        <h2>Scale<sup>{hintList}</sup></h2>
    );
};

export const getTitle = (isEdit: boolean): string => {
    if (isEdit) {
        return "Edit the rating ";
    }
    return "Create your own rating";
};

export const getButtons = (isEdit: boolean, ratingId: string) => {
    if (isEdit) {
        return (
            <div className={CSS_CLASS_BUTTON_GROUP}>
                <EditButton/>
                <CancelButtonLink to={getWebsiteRoutingRatingsById(ratingId)}/>
            </div>
        );
    }
    return (
        <div className={CSS_CLASS_BUTTON_GROUP}>
            <CreateButton/>
            <CancelButtonLink to={WEBSITE_ROUTING_RATINGS}/>
        </div>
    );
};