import React, {useContext} from 'react'
import {useForm} from "react-hook-form";
import {login} from "../../dao/loginRequest";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {Context} from "../../reducers/store";
import {setErrorAC, setUserAC} from "../../constants/actionCreators";

function LoginForm() {

    const {register, handleSubmit, errors} = useForm()
    const [state, dispatch] = useContext(Context)
    const onSubmit = data => login(data)
        .then(resp => dispatch(setUserAC({...resp})))
        .catch(err => setErrorAC(err.response.data.message))

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
            {errors.exampleRequired && <span>This field is required</span>}
            <div>
                <input className="form_field form_button" type="submit" value="Sign In"/>
            </div>
        </form>
    );
}

export default LoginForm