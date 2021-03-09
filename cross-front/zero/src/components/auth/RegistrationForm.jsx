import React from 'react'
import {useForm} from "react-hook-form";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {registration} from "../../dao/loginRequest";

function RegistrationForm() {

    const {register, handleSubmit, errors} = useForm()
    const onSubmit = user => {
        if (checkPasswords(user)) {
            registration(user)
                .then(() => alert("success"))
                .catch(err => console.log(err.message))
        }
    }

    const checkPasswords = (user) => {
        return user.password === user.password_repeat

    }
    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div>
                <input className="form_field" type="text" name="login"
                       ref={register(usernameRestriction)}
                       placeholder="username"/>
            </div>
            <div>
                <input className="form_field" type="password" name="password"
                       ref={register(passwordRestriction)}
                       placeholder="password"/>
            </div>
            <div>
                <input className="form_field" type="password" name="password_repeat"
                       ref={register}
                       placeholder="repeat password"/>
            </div>
            <div>
                <input className="form_field form_button" type="submit" value="Sign In"/>
            </div>
        </form>
    )
}

export default RegistrationForm