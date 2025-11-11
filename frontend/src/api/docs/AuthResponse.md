# AuthResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**requires2FA** | **boolean** |  | [optional] [default to undefined]
**mfaTypes** | **Array&lt;string&gt;** |  | [optional] [default to undefined]
**defaultMfaType** | **string** |  | [optional] [default to undefined]
**challengeId** | **string** |  | [optional] [default to undefined]
**tokenType** | **string** |  | [optional] [default to undefined]
**accessToken** | **string** |  | [optional] [default to undefined]
**expiresIn** | **string** |  | [optional] [default to undefined]
**trustDevice** | **boolean** |  | [optional] [default to undefined]
**rememberMe** | **boolean** |  | [optional] [default to undefined]
**deviceId** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { AuthResponse } from './api';

const instance: AuthResponse = {
    requires2FA,
    mfaTypes,
    defaultMfaType,
    challengeId,
    tokenType,
    accessToken,
    expiresIn,
    trustDevice,
    rememberMe,
    deviceId,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
