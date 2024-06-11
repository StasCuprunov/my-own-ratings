import {FunctionComponent} from "react";
import {DataGrid} from "@mui/x-data-grid";

import {PAGINATION_SIZE_LIST} from "../../constant/DataGridConstants";
import {customizePaginationDefault} from "../../utility/DataGridUtility";
import {editRatingButtonProps, getColumns, goToRatingsButtonProps} from "./RatingFunctions";
import {DeleteRatingDialog} from "./dialog/delete-rating/DeleteRatingDialog";
import {CreateRatingEntryDialog} from "./dialog/rating-entry/form/CreateRatingEntryDialog";
import {EditRatingEntryDialog} from "./dialog/rating-entry/form/EditRatingEntryDialog";
import {DeleteRatingEntryDialog} from "./dialog/rating-entry/delete/DeleteRatingEntryDialog";
import {
    CSS_CLASS_BUTTON_GROUP,
    CSS_CLASS_CONTAINER
} from "../../constant/CSSClassNameConstants";
import {ButtonLinkWithIcon} from "../../component/atom/button/link/ButtonLinkWithIcon";
import {ScaleInformationTemplate} from "./ScaleInformationTemplate";

const columns: any = getColumns();

const initialState: any = customizePaginationDefault();

export const RatingPage: FunctionComponent<any> = ({props}) => {
    return (
        <>
            <div className={CSS_CLASS_CONTAINER}>
                <h1>{props.name}</h1>
                <p>{props.description}</p>
            </div>
            <div className={CSS_CLASS_CONTAINER}>
                <h2>Scale information</h2>
                <ScaleInformationTemplate rangeOfValues={props.rangeOfValues}/>
            </div>
            <div className={CSS_CLASS_CONTAINER}>
                <div className={CSS_CLASS_CONTAINER}>
                    <h2>Operations</h2>
                    <div className={CSS_CLASS_BUTTON_GROUP}>
                        <ButtonLinkWithIcon props={editRatingButtonProps(props.id)}/>
                        <DeleteRatingDialog props={props.deleteRatingDialogProps}/>
                        <ButtonLinkWithIcon props={goToRatingsButtonProps()}/>
                    </div>
                </div>
                <div className={CSS_CLASS_CONTAINER}>
                    <h3>Rating entries</h3>
                    <div className={CSS_CLASS_CONTAINER}>
                        <CreateRatingEntryDialog props={props.createRatingEntryDialogProps}/>
                        <EditRatingEntryDialog props={props.editRatingEntryDialogProps}/>
                        <DeleteRatingEntryDialog props={props.deleteRatingEntryDialogProps}/>
                    </div>
                    <DataGrid autoHeight rows={props.ratingEntriesForDataGrid} columns={columns}
                              initialState={initialState} pageSizeOptions={PAGINATION_SIZE_LIST}
                              disableRowSelectionOnClick onCellClick={props.handleOnCellClick}
                              getRowId={(params) => params.ratingEntry.id}
                    />
                </div>
            </div>
        </>
    );
};