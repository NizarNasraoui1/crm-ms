import { FormControl } from "@angular/forms";

export interface AddContactForm{
    firstName: FormControl<string>,
    lastName :FormControl<string>,
    address: FormControl<string>,
    email: FormControl<string>
}
