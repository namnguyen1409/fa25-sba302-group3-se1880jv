
# MfaDeleteRequest


## Properties

Name | Type
------------ | -------------
`configId` | string
`verificationMethod` | string
`code` | string

## Example

```typescript
import type { MfaDeleteRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "configId": null,
  "verificationMethod": null,
  "code": null,
} satisfies MfaDeleteRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as MfaDeleteRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


