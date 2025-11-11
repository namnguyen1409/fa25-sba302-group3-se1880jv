# PublicKeyCredentialRequestOptions


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**challenge** | **string** |  | [optional] [default to undefined]
**timeout** | **number** |  | [optional] [default to undefined]
**hints** | **Array&lt;string&gt;** |  | [optional] [default to undefined]
**rpId** | **string** |  | [optional] [default to undefined]
**allowCredentials** | [**Array&lt;PublicKeyCredentialDescriptor&gt;**](PublicKeyCredentialDescriptor.md) |  | [optional] [default to undefined]
**userVerification** | **string** |  | [optional] [default to undefined]
**extensions** | [**AssertionExtensionInputs**](AssertionExtensionInputs.md) |  | [optional] [default to undefined]

## Example

```typescript
import { PublicKeyCredentialRequestOptions } from './api';

const instance: PublicKeyCredentialRequestOptions = {
    challenge,
    timeout,
    hints,
    rpId,
    allowCredentials,
    userVerification,
    extensions,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
