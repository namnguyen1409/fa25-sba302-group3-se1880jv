
# PublicKeyCredentialCreationOptions


## Properties

Name | Type
------------ | -------------
`rp` | [RelyingPartyIdentity](RelyingPartyIdentity.md)
`user` | [UserIdentity](UserIdentity.md)
`challenge` | string
`pubKeyCredParams` | [Array&lt;PublicKeyCredentialParameters&gt;](PublicKeyCredentialParameters.md)
`timeout` | number
`hints` | Array&lt;string&gt;
`excludeCredentials` | [Set&lt;PublicKeyCredentialDescriptor&gt;](PublicKeyCredentialDescriptor.md)
`authenticatorSelection` | [AuthenticatorSelectionCriteria](AuthenticatorSelectionCriteria.md)
`attestation` | string
`extensions` | [RegistrationExtensionInputs](RegistrationExtensionInputs.md)

## Example

```typescript
import type { PublicKeyCredentialCreationOptions } from ''

// TODO: Update the object below with actual values
const example = {
  "rp": null,
  "user": null,
  "challenge": null,
  "pubKeyCredParams": null,
  "timeout": null,
  "hints": null,
  "excludeCredentials": null,
  "authenticatorSelection": null,
  "attestation": null,
  "extensions": null,
} satisfies PublicKeyCredentialCreationOptions

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PublicKeyCredentialCreationOptions
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


