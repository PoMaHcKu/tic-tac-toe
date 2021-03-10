import React, {useContext, useRef} from 'react'
import {useForm} from "react-hook-form";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {registration} from "../../dao/loginRequest";
import ErrorComponent from "../ErrorComponent";
import {Context} from "../../reducers/store";
import {setErrorAC} from "../../constants/actionCreators";

function RegistrationForm() {

    const {register, handleSubmit, watch, formState} = useForm(
        {
            mode: 'onChange'
        })
    const [state, dispatch] = useContext(Context)
    const password = useRef({});
    password.current = watch("password", "");
    const onSubmit = user => {
        // if (checkPasswords(user)) {
        registration(user)
            .then(() => alert("success"))
            .catch(err => dispatch(setErrorAC(err.response.data.message)))
        // }
    }

    const checkPasswords = (user) => {
        return user.password === user.password_repeat
    }

    return state.error ? <ErrorComponent message={state.error}/> :
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="form_field_block">
                <label className="form_label" htmlFor="login">Login</label>
                <input className="form_field" type="text" name="login"
                       ref={register(usernameRestriction)}
                       placeholder="username"/>
                <div className="not_validate_field_form">
                    {formState.errors.login &&
                    <span>Must be more characters</span>}
                </div>
            </div>
            <div className="form_field_block">
                <label className="form_label" htmlFor="password">Password</label>
                <input className="form_field" type="password" name="password"
                       ref={register(passwordRestriction)}
                       placeholder="password"/>
                <div className="not_validate_field_form">
                    {formState.errors.password &&
                    <span>Password must be as least 8 chars</span>}
                </div>
            </div>
            <div className="form_field_block">
                <label className="form_label" htmlFor="password_repeat">Password repeat</label>
                <input className="form_field" type="password" name="password_repeat"
                       ref={register({
                           validate: value => password.current === value || "Not match"
                       })}
                       placeholder="repeat password"/>
                <div className="not_validate_field_form">
                    {formState.errors.password_repeat &&
                    <span>{formState.errors.password_repeat.message}</span>}
                </div>
            </div>
            <div>
                <input className="form_field form_button"
                       disabled={!formState.isValid}
                       type="submit"
                       value="Sign In"/>
            </div>
        </form>
}

export default RegistrationForm