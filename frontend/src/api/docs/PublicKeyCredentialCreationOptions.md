# PublicKeyCredentialCreationOptions


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**rp** | [**RelyingPartyIdentity**](RelyingPartyIdentity.md) |  | [optional] [default to undefined]
**user** | [**UserIdentity**](UserIdentity.md) |  | [optional] [default to undefined]
**challenge** | **string** |  | [optional] [default to undefined]
**pubKeyCredParams** | [**Array&lt;PublicKeyCredentialParameters&gt;**](PublicKeyCredentialParameters.md) |  | [optional] [default to undefined]
**timeout** | **number** |  | [optional] [default to undefined]
**hints** | **Array&lt;string&gt;** |  | [optional] [default to undefined]
**excludeCredentials** | [**Set&lt;PublicKeyCredentialDescriptor&gt;**](PublicKeyCredentialDescriptor.md) |  | [optional] [default to undefined]
**authenticatorSelection** | [**AuthenticatorSelectionCriteria**](AuthenticatorSelectionCriteria.md) |  | [optional] [default to undefined]
**attestation** | **string** |  | [optional] [default to undefined]
**extensions** | [**RegistrationExtensionInputs**](RegistrationExtensionInputs.md) |  | [optional] [default to undefined]

## Example

```typescript
import { PublicKeyCredentialCreationOptions } from './api';

const instance: PublicKeyCredentialCreationOptions = {
    rp,
    user,
    challenge,
    pubKeyCredParams,
    timeout,
    hints,
    excludeCredentials,
    authenticatorSelection,
    attestation,
    extensions,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
