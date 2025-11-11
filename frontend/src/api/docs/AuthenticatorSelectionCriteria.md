
# AuthenticatorSelectionCriteria


## Properties

Name | Type
------------ | -------------
`authenticatorAttachment` | string
`requireResidentKey` | boolean
`residentKey` | string
`userVerification` | string

## Example

```typescript
import type { AuthenticatorSelectionCriteria } from ''

// TODO: Update the object below with actual values
const example = {
  "authenticatorAttachment": null,
  "requireResidentKey": null,
  "residentKey": null,
  "userVerification": null,
} satisfies AuthenticatorSelectionCriteria

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AuthenticatorSelectionCriteria
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


