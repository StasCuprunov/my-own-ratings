import {FunctionComponent} from "react";
import {DataGrid} from "@mui/x-data-grid";
import {PAGINATION_SIZE_LIST} from "../../constant/DataGridConstants";
import {customizePaginationDefault} from "../../utility/DataGridUtility";
import {getColumns} from "./RatingFunctions";
import {Button} from "../../component/atom/button/Button";
import {EditButtonLink} from "../../component/atom/button/link/EditButtonLink";
import {getWebsiteRoutingRatingsEditById} from "../../constant/routing/WebsiteRoutingConstants";
import {DeleteRatingDialog} from "./dialog/DeleteRatingDialog";
import {Error} from "../../general-page/error/Error";
import {CreateRatingEntryDialog} from "./dialog/form/CreateRatingEntryDialog";

const columns: any = getColumns();

const initialState: any = customizePaginationDefault();

export const RatingPage: FunctionComponent<any> = ({id, name, description, rangeOfValues, ratingEntries,
                                                       deleteRatingButton, deleteRatingDialogProps, maximumLengthOfName,
                                                       backendError, setBackendError}) => {

    const createRatingEntryDialogProps: any = {
        ratingId: id,
        rangeOfValues: rangeOfValues,
        ratingEntries: ratingEntries,
        maximumLengthOfName: maximumLengthOfName,
        setBackendError: setBackendError
    };

    if (backendError) {
        return (
            <Error error={backendError}/>
        );
    }

    return (
        <div>
            <h1>{name}</h1>
            <p>{description}</p>
            <div>
                <h2>Scale</h2>
                <ul>
                    <li>Maximum: {rangeOfValues.maximum}</li>
                    <li>Minimum: {rangeOfValues.minimum}</li>
                    <li>Step width: {rangeOfValues.stepWidth}</li>
                </ul>
            </div>
            <div>
                <h2>Edit or delete rating</h2>
                <EditButtonLink to={getWebsiteRoutingRatingsEditById(id)}/>
                <Button props={deleteRatingButton}/>
                <DeleteRatingDialog props={deleteRatingDialogProps}/>
            </div>
            <div>
                <h2>Rating entries</h2>
                <CreateRatingEntryDialog props={createRatingEntryDialogProps}/>
                <div>
                    <DataGrid autoHeight rows={ratingEntries} columns={columns} initialState={initialState}
                              pageSizeOptions={PAGINATION_SIZE_LIST} disableRowSelectionOnClick
                    />
                </div>
            </div>
        </div>
    );
};