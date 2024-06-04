import {FunctionComponent} from "react";
import {DataGrid} from "@mui/x-data-grid";

import {PAGINATION_SIZE_LIST} from "../../constant/DataGridConstants";
import {customizePaginationDefault} from "../../utility/DataGridUtility";
import {editRatingButtonProps, getColumns, goToRatingsButtonProps} from "./RatingFunctions";
import {DeleteRatingDialog} from "./dialog/delete-rating/DeleteRatingDialog";
import {Error} from "../../general-page/error/Error";
import {CreateRatingEntryDialog} from "./dialog/rating-entry/form/CreateRatingEntryDialog";
import {EditRatingEntryDialog} from "./dialog/rating-entry/form/EditRatingEntryDialog";
import {DeleteRatingEntryDialog} from "./dialog/rating-entry/delete/DeleteRatingEntryDialog";
import {
    CSS_CLASS_BUTTON_GROUP,
    CSS_CLASS_CONTAINER,
    CSS_CLASS_PARAGRAPH,
    CSS_CLASS_WEBSITE_CONTAINER
} from "../../constant/CSSClassNameConstants";
import {ButtonLinkWithIcon} from "../../component/atom/button/link/ButtonLinkWithIcon";
import {ScaleInformationTemplate} from "./ScaleInformationTemplate";

const columns: any = getColumns();

const initialState: any = customizePaginationDefault();

export const RatingPage: FunctionComponent<any> = ({id, name, description, rangeOfValues, ratingEntriesForDataGrid,
                                                       deleteRatingDialogProps, backendError,
                                                       handleOnCellClick, createRatingEntryDialogProps,
                                                       editRatingEntryDialogProps,
                                                       deleteRatingEntryDialogProps}) => {
    if (backendError) {
        return (
            <Error error={backendError}/>
        );
    }

    return (
        <div className={CSS_CLASS_WEBSITE_CONTAINER}>
            <div className={CSS_CLASS_CONTAINER}>
                <h1>Rating: {name}</h1>
                <p className={CSS_CLASS_PARAGRAPH}>{description}</p>
            </div>
            <div className={CSS_CLASS_CONTAINER}>
                <h2>Scale information</h2>
                <ScaleInformationTemplate rangeOfValues={rangeOfValues}/>
            </div>
            <div className={CSS_CLASS_CONTAINER}>
                <div className={CSS_CLASS_CONTAINER}>
                    <h2>Operations</h2>
                    <div className={CSS_CLASS_BUTTON_GROUP}>
                        <ButtonLinkWithIcon props={editRatingButtonProps(id)}/>
                        <DeleteRatingDialog props={deleteRatingDialogProps}/>
                        <ButtonLinkWithIcon props={goToRatingsButtonProps()}/>
                    </div>
                </div>
                <div className={CSS_CLASS_CONTAINER}>
                    <h3>Rating entries</h3>
                    <div className={CSS_CLASS_CONTAINER}>
                        <CreateRatingEntryDialog props={createRatingEntryDialogProps}/>
                        <EditRatingEntryDialog props={editRatingEntryDialogProps}/>
                        <DeleteRatingEntryDialog props={deleteRatingEntryDialogProps}/>
                    </div>
                    <DataGrid autoHeight rows={ratingEntriesForDataGrid} columns={columns} initialState={initialState}
                              pageSizeOptions={PAGINATION_SIZE_LIST} disableRowSelectionOnClick
                              onCellClick={handleOnCellClick}
                              getRowId={(params) => params.ratingEntry.id}
                    />
                </div>
            </div>
        </div>
    );
};