import {FunctionComponent} from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import {CreateButtonLink} from "../component/atom/button/CreateButtonLink";
import {ButtonLink} from "../component/atom/button/ButtonLink";
import {WEBSITE_ROUTING_RATINGS_CREATE} from "../constant/routing/WebsiteRoutingConstants";
import {getButtonLinkToRating} from "./StartPageFunctions";

const createButtonLink: any = {
    to: WEBSITE_ROUTING_RATINGS_CREATE
}

export const StartPagePage: FunctionComponent<any> = ({name, ratingDTOs}) => {

    return (
        <div>
            <h1>Hello {name}!</h1>
            <div>
                <h2>Create a new rating</h2>
                <CreateButtonLink props={createButtonLink}/>
            </div>
            <div>
                <h2>Your ratings</h2>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Name</TableCell>
                                <TableCell>Description</TableCell>
                                <TableCell>Link</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {ratingDTOs.map((rating: any) => (
                                <TableRow key={rating.id}>
                                    <TableCell>{rating.name}</TableCell>
                                    <TableCell>{rating.description}</TableCell>
                                    <TableCell><ButtonLink props={getButtonLinkToRating(rating.id)}/></TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
    );
};