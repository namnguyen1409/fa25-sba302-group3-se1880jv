# RegistrationExtensionInputs


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**appidExclude** | [**AppId**](AppId.md) |  | [optional] [default to undefined]
**credProps** | **boolean** |  | [optional] [default to undefined]
**credentialProtectionPolicy** | **string** |  | [optional] [default to undefined]
**enforceCredentialProtectionPolicy** | **boolean** |  | [optional] [default to undefined]
**largeBlob** | [**LargeBlobRegistrationInput**](LargeBlobRegistrationInput.md) |  | [optional] [default to undefined]
**prf** | [**PrfRegistrationInput**](PrfRegistrationInput.md) |  | [optional] [default to undefined]
**uvm** | **boolean** |  | [optional] [default to undefined]

## Example

```typescript
import { RegistrationExtensionInputs } from './api';

const instance: RegistrationExtensionInputs = {
    appidExclude,
    credProps,
    credentialProtectionPolicy,
    enforceCredentialProtectionPolicy,
    largeBlob,
    prf,
    uvm,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
