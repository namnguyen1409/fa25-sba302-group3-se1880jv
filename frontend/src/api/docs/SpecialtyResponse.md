
# SpecialtyResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`name` | string
`description` | string
`department` | [DepartmentResponse](DepartmentResponse.md)

## Example

```typescript
import type { SpecialtyResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "name": null,
  "description": null,
  "department": null,
} satisfies SpecialtyResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as SpecialtyResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


